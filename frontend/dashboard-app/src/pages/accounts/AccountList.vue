<template>
  <table>
    <thead>
      <tr>
        <th>ID</th>
        <th>Username</th>
        <th>Email</th>
        <th>First Name</th>
        <th>Last Name</th>
      </tr>
    </thead>
    <tbody>
      <AccountItem v-for="account in accounts" :key="account.id" :account="account" />
    </tbody>
  </table>
</template>

<script setup lang="ts">
import { defineProps } from 'vue';
import AccountItem from './AccountItem.vue';
import type { AccountDto } from "../../services/AccountService";

defineProps<{
  accounts: AccountDto[];
}>();
</script>
