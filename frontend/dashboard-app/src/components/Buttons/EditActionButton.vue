<template>
  <EvilActionButton
    :disabled="processing"
    backgroundColor="#3b82f6"       
    frontColor="#ffffff"
    hoverBackgroundColor="#2563eb"   
    hoverFrontColor="#ffffff"
    :icon="PencilIcon"
    text="View"
    @click="handleClick"
  />
</template>

<script setup lang="ts">
import EvilActionButton from './EvilActionButton.vue'
import { PencilIcon } from '@heroicons/vue/24/solid'
import { ref } from 'vue'

const processing = ref(false)
const emit = defineEmits(['click'])

function handleClick() {
  emit('click')
}
</script>
