<template>
  <EvilActionButton
    :disabled="processing"
    backgroundColor="#22c55e"
    frontColor="#ffffff"
    hoverBackgroundColor="#15803d"
    hoverFrontColor="#ffffff"
    :icon="CheckIcon"
    :text="text"
    @click="handleClick"
  />
</template>

<script setup lang="ts">
import EvilActionButton from './EvilActionButton.vue'
import { CheckIcon } from '@heroicons/vue/24/solid'
import { ref } from 'vue'

const processing = ref(false)
const props = defineProps<{ text?: string }>()
const text = props.text || ''
const emit = defineEmits(['click'])

function handleClick() {
  processing.value = true;
  emit('click');
  processing.value = false;
}
</script>
