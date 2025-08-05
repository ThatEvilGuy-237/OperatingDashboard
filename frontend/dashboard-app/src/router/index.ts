import { createRouter, createWebHistory } from 'vue-router';
import DashboardView from '../pages/DashboardView.vue';
import AboutView from '../pages/AboutView.vue';
import AccountsPage from '../pages/accounts/AccountsPage.vue';
import AccountDetailPage from '../pages/accounts/AccountDetailPage.vue';
import AuthService from '../services/AuthService';

const LOGIN_URL = import.meta.env.VITE_LOGIN_URL;

const routes = [
  {
    path: '/dashboard',
    name: 'Dashboard',
    component: DashboardView
  },
  {
    path: '/about',
    name: 'About',
    component: AboutView
  },
  {
    path: '/accounts',
    name: 'Accounts',
    component: AccountsPage
  },
  {
    path: '/accounts/:id',
    name: 'account-detail',
    component: AccountDetailPage
  },
  { 
    path: '/',
    redirect: '/dashboard'
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

// VALIDATE TOKEN
// check if the user is authenticated or not
router.beforeEach(async (to, from, next) => {
  const isValid = await AuthService.validateToken();
  console.log('Token validation result:', isValid);
  if (!isValid) {
    // If token is not valid. Redirect to login page
    window.location.href = LOGIN_URL;
   console.warn('Token is not valid, redirecting to login page');
    return;
  }

  next();
});

export default router;