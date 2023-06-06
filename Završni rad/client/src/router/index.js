import { createRouter, createWebHistory } from 'vue-router'
import WorldRecords from '../views/WorldRecords.vue'
import HomePage from '../views/HomePage.vue'
import QualifiedAthletes from '../views/QualifiedAthletes.vue'
import Pools from '../views/Pools.vue'
import Swimmers from '../views/Swimmers.vue'
import SwimmersPage from '../views/SwimmersPage.vue'
import Competitions from '../views/Competitions'
import CompetitionPage from '../views/CompetitionPage'
import ChartComponent from '../views/ChartComponent'

const routes = [
  {
    path: '',
    name: 'HomePage',
    component: HomePage
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
    path: '/swimmers',
    name: 'Swimmers',
    component: Swimmers
  },
  {
    path: '/swimmers/:ime/:prezime',
    name: 'SwimmersPage',
    component: SwimmersPage
  },
  {
    path: '/competitions',
    name: 'Competitions',
    component: Competitions
  },
  {
    path: '/competitions/chart/:id',
    name: 'ChartComponent',
    component: ChartComponent
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
