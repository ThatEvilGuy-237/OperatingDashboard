<template>
<div>
    <h1>Account</h1>
</div>
<div v-if="account"> 
    <div>{{ account.id }}</div>
    <div>{{ account.username }}</div>
    <div>{{ account.email }}</div>
    <div>{{ account.firstName }}</div>
    <div>{{ account.lastName }}</div>
    <div>{{ account.phoneNumber }}</div>
    <div>{{ account.validated ? "Yes" : "No" }}</div>
    <div>{{ account.locked ? "Yes" : "No" }}</div>
    <div>{{ account.jobTitle ? (account.jobTitle.emote + account.jobTitle.title) : "N/A" }}</div>
    <div>
      <span
        v-for="(role, index) in account.roles"
        :key="role.id"
      >
        {{ role.name }}<span v-if="!Utils.isLast(index, account.roles.length)">, </span>
      </span>
    </div>
    <div>{{ Utils.formatDate(account.accountCreated) }}</div>
    <div>{{ Utils.formatDate(account.lastLogin) }}</div>
</div>
</template>

<script setup lang="ts">
import { onMounted } from 'vue'
import { useRoute } from 'vue-router'
import type { AccountDto } from '../../interfaces/Account';
import { AccountService } from '../../services/AccountService';
import Utils from '../../utils/Utils';
const route = useRoute();
let id:number = -1;
import { ref } from 'vue';

const account = ref<AccountDto | null>(null);


function getIdFromUrl(paramiters:any) :number{
    return Number(paramiters.id);
}
async function getAccount() {
  account.value = await AccountService.getAccountById(id);
}

onMounted(() => {
    id = getIdFromUrl(route.params);
    getAccount();
})

</script>

<style scoped> 

</style>