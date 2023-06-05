<template>
    <v-card
        class="mx-auto"
        max-width="344"
    >
            <v-img
                :src="getImagePath(pool.nazivbazen)"
                height="200px"
                cover
            ></v-img>

            <v-card-title class="multi-row-text">
                {{ pool.idbazen + ' - ' + pool.nazivbazen }}
            </v-card-title>

            <v-card-subtitle>
                {{ pool.drzava.nazivdrzava }}
            </v-card-subtitle>

            <v-card-actions>
                <v-btn
                    color="orange-lighten-2"
                    variant="text"
                    @click="show = !show"
                >
                    Explore
            </v-btn>

            <v-spacer></v-spacer>

            <v-btn
                :icon="show ? 'mdi-chevron-up' : 'mdi-chevron-down'"
                @click="show = !show"
            ></v-btn>
            </v-card-actions>

            <v-expand-transition>
            <div v-show="show">
                <v-divider></v-divider>

                <v-card-text>
                    {{ 'City: ' + pool.grad }} <br>
                    {{ 'Address: ' + pool.adresa }} <br>
                    {{ 'Capacity: ' + pool.kapacitet }}
                </v-card-text>
            </div>
            </v-expand-transition>
        </v-card>
</template>
<script>
export default {
    name: 'PoolCard',
    props: {
        pool: Object,
    },
    data() {
        return {
            show: false,
        }
    },
    methods: {
        getImagePath(image) {
            return require('@/assets/pools/' + image + '.jpg');
        }
   }
    
}
</script>
<style scoped>
.multi-row-text {
  white-space: pre-wrap; 
  word-break: break-word;
  min-height: 4em;
}
</style>