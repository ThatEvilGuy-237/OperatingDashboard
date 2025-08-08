<template>
  <div>
    <input type="text" placeholder="Search(not working)..."> 
    <button>Search</button>
  </div>
  
  <!-- Table -->
  <table>
    <thead>
      <tr>
        <th v-for="header in headers" :key="header">{{ header }}</th>
      </tr>
    </thead>
    <tbody>
      <AccountItem v-for="account in accounts" :key="account.id" :account="account" />
    </tbody>
  </table>  
</template>

<script setup lang="ts">
import type { AccountDto } from '../../../interfaces/Account';
import AccountItem from './AccountItem.vue';

// change this for dirent langue handeling
const headers = [
  "ID",
  "Username", 
  "Email",
  "First Name",
  "Last Name",
  "Phone Number",
  "Validated",
  "Locked",
  "Job Title",
  "Roles",
  "Account Created",
  "Last Login",
  ""
];

defineProps<{ accounts: AccountDto[] }>();
</script>

<style scoped>
table {
  width: 100%;
  border-collapse: collapse;
  border: 1px solid #595959;
  background-color: var(--wite);
  color: #333;
  font-size: 14px;
}

</style>