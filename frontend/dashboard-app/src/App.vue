<template>
  <div id="app">
    <nav class="text-align-center">
      <router-link to="/dashboard">Dashboard</router-link> |
      <router-link to="/about">About</router-link> |
      <router-link to="/accounts">Accounts</router-link>
    </nav>
    <div class="dashboard-example">
      <router-view />
    </div>
    <div class="text-align-center">
      <p>© 2025 Your Company Name. All rights reserved.</p>
    </div>
  </div>
</template>

<script setup lang="ts">
</script>

<style scoped>

#app {
  background-color: #657ba1;
  display: flex;
  flex-direction: column;
  padding: 5%;
  min-height: 100vh;
}

.text-align-center {
  text-align: center;
}

</style>
