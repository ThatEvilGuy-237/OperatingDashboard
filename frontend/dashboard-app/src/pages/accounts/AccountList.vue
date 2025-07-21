<script setup lang="ts">
import type { AccountDto } from '../../interfaces/Account';
import AccountItem from './AccountItem.vue';

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
  "Last Login"
];

defineProps<{ accounts: AccountDto[] }>();
</script>

<template>
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
