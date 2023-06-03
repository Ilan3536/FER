<template>
    <v-card
        class="mx-auto"
        max-width="344"
    >
            <v-img
                :src="getImagePath(pool.name)"
                height="200px"
                cover
            ></v-img>

            <v-card-title>
                {{ pool.id + ' - ' + pool.name }}
            </v-card-title>

            <v-card-subtitle>
                {{ pool.country }}
            </v-card-subtitle>

            <v-card-actions>
                <v-btn
                    color="orange-lighten-2"
                    variant="text"
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
                    {{ 'City: ' + pool.city }} <br>
                    {{ 'Address: ' + pool.address }} <br>
                    {{ 'Capacity: ' + pool.capacity }}
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
<style>
    
</style>