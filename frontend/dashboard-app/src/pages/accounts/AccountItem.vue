<template>
  <tr>
    <td>{{ account.id }}</td>
    <td>{{ account.username }}</td>
    <td>{{ account.email }}</td>
    <td>{{ account.firstName }}</td>
    <td>{{ account.lastName }}</td>
  </tr>
</template>

<script setup lang="ts">
import type { AccountDto } from "../../services/AccountService";

defineProps<{
  account: AccountDto;
}>();
</script>
