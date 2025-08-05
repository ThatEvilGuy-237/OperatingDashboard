<template>
  <EvilActionButton
    :disabled="processing"
    backgroundColor="#ef4444"
    frontColor="#ffffff"
    hoverBackgroundColor="#b91c1c"
    hoverFrontColor="#ffffff"
    :icon="TrashIcon"
    text="Delete"
    @click="handleDelete"
  />
</template>

<script setup lang="ts">
import EvilActionButton from './EvilActionButton.vue'
import { TrashIcon } from '@heroicons/vue/24/solid'
import { ref } from 'vue'

const processing = ref(false)

function handleDelete() {
  if (processing.value) return
  processing.value = true

  // delete logic here
  setTimeout(() => {
    processing.value = false
  }, 2000)
}
</script>
