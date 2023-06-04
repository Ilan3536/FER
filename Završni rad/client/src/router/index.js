import { createRouter, createWebHistory } from 'vue-router'
import Rezultati from '../views/Rezultati.vue'
import WorldRecords from '../views/WorldRecords.vue'
import HomePage from '../views/HomePage.vue'
import QualifiedAthletes from '../views/QualifiedAthletes.vue'
import Pools from '../views/Pools.vue'
import Competitions from '../views/Competitions'
import CompetitionPage from '../views/CompetitionPage'

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
  {
    path: '/competitions',
    name: 'Competitions',
    component: Competitions
  },
  {
    path: '/competitions/:id',
    name: 'CompetitionPage',
    component: CompetitionPage
  },
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
