import { createRouter, createWebHistory } from 'vue-router'
import Rezultati from '../views/Rezultati.vue'
import WorldRecords from '../views/WorldRecords.vue'
import HomePage from '../views/HomePage.vue'
import QualifiedAthletes from '../views/QualifiedAthletes.vue'
import Pools from '../views/Pools.vue'

const routes = [
  {
    path: '',
    name: 'HomePage',
    component: HomePage
  },
  {
    path: '/rezultati',
    name: 'Rezultati',
    component: Rezultati
  },
  {
    path: '/world-records',
    name: 'World Records',
    component: WorldRecords
  },
  {
    path: '/qualified-athletes',
    name: 'Qualified Athletes',
    component: QualifiedAthletes
  },
  {
    path: '/pools',
    name: 'Pools',
    component: Pools
  },
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
