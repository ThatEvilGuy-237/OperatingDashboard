import { createApp } from 'vue'
import App from './App.vue'
import router from './router';
import '@shared/cssrest.css';
import '@shared/colorpallet.css';

createApp(App).use(router).mount('#app')
