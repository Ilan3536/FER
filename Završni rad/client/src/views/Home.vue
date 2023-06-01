<template>
    <div>
        <h1>Welcome to the Home Page! {{ count }}</h1>
        
        <button @click="increment">Incremenet Count</button>
        <button @click="fetchRezultati">Fetch Rezultati</button>
        <ul>
            <li v-for="rezultat in rezultati" :key="rezultat.idrezultat">
                {{rezultat.vrijeme}}
            </li>
        </ul>
    </div>
</template>
<script>
import {mapState, mapMutations, mapActions} from 'vuex'

export default {
    name: 'HomeView',
    computed: {
        ...mapState('count', ['count']),
        ...mapState('rezultati', ['rezultati'])
    },
    methods: {
        ...mapMutations('count', ['increment']),
        ...mapActions('rezultati', ['fetchRezultati']),
    }
};
</script>


<style>
    
</style>