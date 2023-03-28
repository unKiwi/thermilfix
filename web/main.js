
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
            
            let date1 = new Date(this.date1);
            let date2 = new Date(this.date2);

            if (date1 > date2) {
                let dateTemp = date1;
                date1 = date2;
                date2 = dateTemp;
            }

            let sumChaudiere = 0;
            this.interventions[this.selectedTechnicienForNbIntervention].forEach(intervention => {
                let interventionDate = new Date(intervention.created_at);
                if (interventionDate > date1 && interventionDate < date2) {
                    sumChaudiere += intervention.intervention.length;
                }
            });

            this.nbChaudiereReparer = `${this.selectedTechnicienForNbIntervention} a réparé ${sumChaudiere} chaudière${sumChaudiere > 1 ? "s" : ""}`;
        }
    }
}).mount('#app');