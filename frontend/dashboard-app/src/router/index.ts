import { createRouter, createWebHistory } from 'vue-router';
import DashboardView from '../pages/DashboardView.vue';
import AboutView from '../pages/AboutView.vue';

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
    path: '/',
    redirect: '/dashboard'
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;