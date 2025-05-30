import { createRouter, createWebHistory } from 'vue-router';
import LoginPage from '../pages/LoginPage.vue';

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: LoginPage,
  },
  // {
  //   path: '/somthing else',
  //   name: 'Somthing',
  //   component: LoginPage,
  // },
  // Optionally, redirect root to login
  { path: '/', redirect: '/login' },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;