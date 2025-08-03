<template>
    <div class="btn-container">
        <button :disabled="disabled" :class="['base-btn', disabled ? 'btn-disabled' : 'btn-enabled']"
            @click="handleClick" :style="{
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
        <span v-if="tooltip !== ''" class="tooltip">{{ tooltip }}</span>
    </div>
</template>


<script setup lang="ts">
import { defineProps, defineEmits, type Component } from 'vue'

const props = defineProps({
    disabled: { type: Boolean, default: false },
    backgroundColor: { type: String, default: '#3b82f6' },
    frontColor: { type: String, default: 'white' },
    hoverBackgroundColor: { type: String, default: '#2563eb' },
    hoverFrontColor: { type: String, default: 'white' },
    icon: { type: Object as () => Component | null, default: null },
    text: { type: String, default: '' },
    tooltip: { type: String, default: '' },
})

const buttonPress = defineEmits(['click'])

const handleClick = (event: MouseEvent) => {
    if (!props.disabled) {
        buttonPress('click', event)
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

/* tool tip */
.btn-container {
    position: relative;
    display: inline-block;
}

.tooltip {
    position: absolute;
    bottom: 120%;
    /* above the button */
    left: 50%;
    transform: translateX(-50%);
    background-color: black;
    color: white;
    padding: 4px 8px;
    border-radius: 4px;
    white-space: nowrap;
    font-size: 12px;
    opacity: 0;
    pointer-events: none;
    transition: opacity 0.2s ease;
    z-index: 100;
}

.tooltip-container:hover .tooltip {
    opacity: 1;
}


/* icon + text */
.icon {
    display: inline-flex;
    width: 1em;
    /* or fixed pixel size like 16px */
    height: 1em;
    align-items: center;
    justify-content: center;
    /* prevent layout jump when icon missing */
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
