<template>
  <div id="app">
    <nav>
      <router-link to="/dashboard">Dashboard</router-link> |
      <router-link to="/about">About</router-link>
    </nav>
    <div class="dashboard-example">
      <router-view />
    </div>
    <div class="footer">
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
  min-height: 100vh;
  text-align: center;
}


</style>
