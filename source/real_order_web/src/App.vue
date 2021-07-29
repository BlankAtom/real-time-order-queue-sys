<template>
    <div class="app" v-cloak>
        <router-view />
    </div>
</template>

<script>
import { onMounted } from "vue";
import { useStore } from "vuex";
import { decode } from "js-base64";

export default {
    setup() {
        const store = useStore();

        onMounted(() => {
            let state = sessionStorage.getItem("state") || ""
            if( state) {
                store.replaceState(Object.assign({}, store.state, JSON.parse(state)))
            }
            console.log(state)
        });
    }
};
</script>
