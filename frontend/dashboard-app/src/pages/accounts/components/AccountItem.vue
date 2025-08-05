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
        {{ role.name }}<span v-if="!Utils.isLast(index, account.roles.length)">, </span>
      </span>
    </td>
    <td>{{ Utils.formatDate(account.accountCreated) }}</td>
    <td>{{ Utils.formatDate(account.lastLogin) }}</td>

    <td><EditActionButton @click="openAccountDetails(account.id)" /></td>
  </tr>
</template>

<script setup lang="ts">
import type { AccountDto } from "../../../interfaces/Account";
import EditActionButton from "../../../components/Buttons/EditActionButton.vue";
import Utils from "../../../utils/Utils";
import { useRouter } from "vue-router";
const router = useRouter();

function openAccountDetails(accountId: number) {
router.push({ name: 'account-detail', params: { id: accountId } })

  console.log(accountId);
}

defineProps<{
  account: AccountDto;
}>();
</script>

<style scoped>
.role-name {
  white-space: nowrap;
}

 td {
  border: 1px solid #3c3c3c;
  text-align: left;
}
</style>
