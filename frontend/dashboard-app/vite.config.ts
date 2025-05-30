import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import path from 'path'
// https://vite.dev/config/
export default defineConfig({
  plugins: [vue()],
   resolve: {
    alias: {
      '@shared': path.resolve(__dirname, '../shared-assets')
    }
  }
})
