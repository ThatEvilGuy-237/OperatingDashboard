<template>
  <EvilActionButton
    :disabled="processing"
    backgroundColor="#3b82f6"
    frontColor="#ffffff"
    hoverBackgroundColor="#2563eb"
    hoverFrontColor="#ffffff"
    :icon="PencilIcon"
    :text="text"
    @click="handleClick"
  />
</template>

<script setup lang="ts">
import EvilActionButton from './EvilActionButton.vue'
import { PencilIcon } from '@heroicons/vue/24/solid'
import { ref } from 'vue'

const emit = defineEmits(['click'])
const props = defineProps<{ text?: string }>()
const text = props.text || ''
const processing = ref(false)

function handleClick() {
  if (processing.value) return
  processing.value = true
  emit('click')
  processing.value = false
}
</script>
