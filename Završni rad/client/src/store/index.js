import { createStore } from 'vuex';
import count from './modules/count'
import rezultati from './modules/rezultati'
import rekordi from './modules/rekordi'
import limiti from './modules/limiti'
import bazeni from './modules/bazeni'
import natjecanja from './modules/natjecanja'

const store = createStore({
    modules: {
        count: count,
        rezultati: rezultati,
        rekordi: rekordi,
        limiti: limiti,
        bazeni: bazeni,
        natjecanja: natjecanja,
    }
})

export default store