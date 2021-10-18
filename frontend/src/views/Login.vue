<template>
    <v-container fluid fill-height class="primary d-flex justify-center align-center">
        <v-card width="400">
            <v-card-title>Iniciar sesion</v-card-title>
            <v-card-actions class="px-4 pb-4">
                <v-form class="flex">
                    <v-text-field
                        label="Username"
                    ></v-text-field>
                    <v-text-field
                        label="Password"
                        type="password"
                    ></v-text-field>
                    <v-btn block color="primary">Continuar</v-btn>
                </v-form>
            </v-card-actions>
        </v-card>
    </v-container>
</template>

<script>
export default {
    name: "Login"
}
</script>

<style scoped>

</style>