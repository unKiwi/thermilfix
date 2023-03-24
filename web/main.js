const { createApp } = Vue

createApp({
    data() {
        return {
            selectedTechnitien: "Robert",
            technitiens: [
                "Robert",
                "Didier",
                "Sam",
                "Francis",
            ],
            
        }
    }
}).mount('#app')