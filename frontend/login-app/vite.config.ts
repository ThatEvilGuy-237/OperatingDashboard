import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import path from 'path'
// https://vite.dev/config/
export default defineConfig({
  plugins: [vue()],
  server: {
    port: 5173
  },
  resolve: {
    alias: {
      // '@': path.resolve(__dirname, './src'),
      '@shared': path.resolve(__dirname, '../shared-assets')
    }
  }
})
