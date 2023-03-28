
const { createApp } = Vue;

createApp({
    data() {
        return {
            selectedTechnicien: "",
            interventions: {},
            selectedTechnicienForNbIntervention: "",
            date1: undefined,
            date2: undefined,
            nbChaudiereReparer: "",
        }
    },
    mounted() {
        this.getInterventions();
    },
    methods: {
        technitiens() {
            return Object.keys(this.interventions);
        },
        async getInterventions() {
            let interventions = await fetch("http://localhost:8000/api/");
            let json = await interventions.json();

            // parser interpreter et trier le json
            json.forEach(intervention => {
                if (!Array.isArray(this.interventions[intervention.name])) {
                    this.interventions[intervention.name] = [];
                }

                this.interventions[intervention.name].push(intervention);

                // reformater intervention
                delete intervention.name;
                intervention.intervention = JSON.parse(intervention.intervention);
            });

            this.selectedTechnicien = this.technitiens()[0];
            this.selectedTechnicienForNbIntervention = this.selectedTechnicien;
        },
        calcNbChaudiere() {
            if (this.date1 == undefined || this.date2 == undefined) return;
            
            
        }
    }
}).mount('#app');