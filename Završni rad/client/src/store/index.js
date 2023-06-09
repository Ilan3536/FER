import { createStore } from 'vuex';
import rezultati from './modules/rezultati'
import rekordi from './modules/rekordi'
import limiti from './modules/limiti'
import bazeni from './modules/bazeni'
import natjecanja from './modules/natjecanja'
import discipline from './modules/discipline'
import osobe from './modules/osobe'

const store = createStore({
    modules: {
        rezultati: rezultati,
        rekordi: rekordi,
        limiti: limiti,
        bazeni: bazeni,
        natjecanja: natjecanja,
        discipline: discipline,
        osobe: osobe,
    }
})

export default store