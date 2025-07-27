<template>
    <button :disabled="disabled" 

    :class="['base-btn', disabled ? 'btn-disabled' : 'btn-enabled']"

    @click="handleClick"
    
        :style="{
            '--bg-color': backgroundColor,
            '--front-color': frontColor,
            '--hover-bg-color': hoverBackgroundColor,
            '--hover-front-color': hoverFrontColor,
        }">
        
        <span class="icon" v-if="icon">
            <component :is="icon" />
        </span>

        <span v-if="text" class="text">{{ text }}</span>
    </button>
</template>


<script setup lang="ts">
import { defineProps, defineEmits } from 'vue'

const props = defineProps({
    disabled: { type: Boolean, default: false },
    backgroundColor: { type: String, default: '#3b82f6' },
    frontColor: { type: String, default: 'white' },
    hoverBackgroundColor: { type: String, default: '#2563eb' },
    hoverFrontColor: { type: String, default: 'white' },
    icon: { type: Object, required: true }, // expects a component
    text: { type: String, default: '' },
})

const emits = defineEmits(['click'])

const handleClick = (event: MouseEvent) => {
    if (!props.disabled) {
        emits('click', event)
    }
}
</script>


<style scoped>
.base-btn {
    display: inline-flex;
    align-items: center;
    gap: 6px;
    font-weight: 600;
    border: none;
    background-color: var(--bg-color);
    color: var(--front-color);
    font-family: sans-serif;
    font-size: 14px;
    transition: background-color 0.2s ease, color 0.2s ease, opacity 0.2s ease;
    user-select: none;
}

/* icon + text */
.icon {
    
}

.text {
    
}

/* Enabled button style */
.btn-enabled {
    cursor: pointer;
    opacity: 1;
}

.btn-enabled:hover {
    background-color: var(--hover-bg-color);
    color: var(--hover-front-color);
}

/* Disabled button style */
.btn-disabled {
    cursor: not-allowed;
    opacity: 0.6;
    pointer-events: none;
}
</style>
