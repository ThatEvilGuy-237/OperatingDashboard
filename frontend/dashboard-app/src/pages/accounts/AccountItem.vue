<template>
  <tr>
    <td>{{ account.id }}</td>
    <td>{{ account.username }}</td>
    <td>{{ account.email }}</td>
    <td>{{ account.firstName }}</td>
    <td>{{ account.lastName }}</td>
    <td>{{ account.phoneNumber }}</td>
    <td>{{ account.validated ? "Yes" : "No" }}</td>
    <td>{{ account.locked ? "Yes" : "No" }}</td>
    <td>{{ account.jobTitle ? (account.jobTitle.emote + account.jobTitle.title) : "N/A" }}</td>

    <td>
      <span
        v-for="(role, index) in account.roles"
        :key="role.id"
        class="role-name"
      >
        {{ role.name }}<span v-if="!isLast(index, account.roles.length)">, </span>
      </span>
    </td>
    <td>{{ formatDate(account.accountCreated) }}</td>
    <td>{{ formatDate(account.lastLogin) }}</td>
  </tr>
</template>

<script setup lang="ts">
import type { AccountDto } from "../../interfaces/Account";

defineProps<{
  account: AccountDto;
}>();

function formatDate(date: string | null | undefined): string {
  if (!date) return "N/A";
  return new Date(date).toLocaleString();
}

function isLast(index: number, length: number): boolean {
  return index === length - 1;
}
</script>

<style scoped>
.role-name {
  white-space: nowrap;
}
</style>
