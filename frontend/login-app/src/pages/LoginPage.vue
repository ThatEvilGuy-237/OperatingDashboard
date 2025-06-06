<template>
  <div class="login-page">
    <h2>Login</h2>
    <form @submit.prevent="handleLogin">
      <div>
        <label for="identifier">Email or Username</label>
        <input type="text" id="identifier" v-model="identifier" required />
      </div>
      <div>
        <label for="password">Password</label>
        <input type="password" id="password" v-model="password" required />
      </div>
      <button type="submit">Login</button>
    </form>
    <p v-if="errorMessage" class="error">{{ errorMessage }}</p>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import AuthService from '../services/AuthService';

const identifier = ref('');
const password = ref('');
const errorMessage = ref('');

const handleLogin = async () => {
  if (identifier.value && password.value) {
    console.log(identifier.value, password.value);
    const success: boolean = await AuthService.login(identifier.value.trim(), password.value.trim());
    console.log('Login success:', success);
    if(success) {
      // Redirect to the home page or dashboard
      window.location.href = '/';
    } else {
      errorMessage.value = 'Invalid email/username or password.';
    }

  }
};
</script>

<style scoped>
.login-page {
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.error {
  color: red;
}
</style>

