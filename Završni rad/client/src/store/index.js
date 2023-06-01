import { createStore } from 'vuex';
import count from './modules/count'
import rezultati from './modules/rezultati'

const store = createStore({
    modules: {
        count: count,
        rezultati: rezultati,
    }
})

export default store