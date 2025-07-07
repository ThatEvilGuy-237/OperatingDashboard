<template>
  <div>
    <h1>Accounts</h1>
    <AccountList :accounts="accounts" />
    <div class="pagination">
      <button @click="prevPage" :disabled="currentPage === 0">Previous</button>
      <span>Page {{ currentPage + 1 }} of {{ totalPages }}</span>
      <button @click="nextPage" :disabled="currentPage + 1 >= totalPages">Next</button>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import {AccountService, type AccountDto } from "../../services/AccountService";
import AccountList from './AccountList.vue';

const accounts = ref<AccountDto[]>([]);
const currentPage = ref(0);
const pageSize = 10;
const totalPages = ref(1);

async function loadPage(page = 0) {
  const data = await AccountService.getAccounts(page, pageSize);
  accounts.value = data.content;
  totalPages.value = data.totalPages;
  currentPage.value = data.number;
}

function nextPage() {
  if (currentPage.value + 1 < totalPages.value) {
    loadPage(currentPage.value + 1);
  }
}

function prevPage() {
  if (currentPage.value > 0) {
    loadPage(currentPage.value - 1);
  }
}

onMounted(() => {
  loadPage();
});
</script>

<style scoped>
.pagination {
  margin-top: 1em;
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 1em;
}
button:disabled {
  opacity: 0.5;
  cursor: not-allowed;
}
</style>
