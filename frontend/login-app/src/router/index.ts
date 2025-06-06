import { createRouter, createWebHistory } from 'vue-router';
import LoginPage from '../pages/LoginPage.vue';
import AuthService from '../services/AuthService';

const DASHBOARD_URL = import.meta.env.VITE_DASHBOARD_URL;

const routes = [
  {
    path: '/',
    redirect: '/login',
  },
  {
    path: '/login',
    name: 'Login',
    component: LoginPage,
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

// Global navigation guard using validateToken
router.beforeEach(async (to, from, next) => {
  const isValid = await AuthService.validateToken();

  if (isValid) {
    // If token is valid, redirect to dashboard
    window.location.href = DASHBOARD_URL;
    return;
  }

  // If token is not valid, go to login page
  if (to.path === '/' || to.path === '/login') {
    next();
  } else {
    next('/');
  }
});

export default router;