// const { createApp } = Vue;


(async function () {
    let interventions = await fetch("http://localhost:8000/api/");
    console.log(await interventions.json())
})()

// (async function () {
//     let interventions = await fetch("http://localhost:8000/api/", {mode: 'no-cors', method: "POST"});
//     console.log(interventions)
// })()
// fetch('http://localhost:8000/api/', {
//     mode: 'no-cors',
//     headers: {
//         'Accept': 'application/json',
//         'Content-Type': 'application/json'
//     },
// })
//     .then((response) => response.json())
//     .then((data) => {
//         console.log(data);
//     })
//     .finally((data) => {
//         console.log(data);
//     })

// fetch("http://localhost:8000/api/", {
//     "headers": {
//         "accept": "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8",
//         "accept-language": "ru-RU,ru",
//         "cache-control": "no-cache",
//         "pragma": "no-cache",
//         "sec-ch-ua": "\"Brave\";v=\"111\", \"Not(A:Brand\";v=\"8\", \"Chromium\";v=\"111\"",
//         "sec-ch-ua-mobile": "?0",
//         "sec-ch-ua-platform": "\"macOS\"",
//         "sec-fetch-dest": "document",
//         "sec-fetch-mode": "navigate",
//         "sec-fetch-site": "none",
//         "sec-fetch-user": "?1",
//         "sec-gpc": "1",
//         "upgrade-insecure-requests": "1"
//     },
//     "referrerPolicy": "strict-origin-when-cross-origin",
//     "body": null,
//     "method": "GET",
//     "mode": "no-cors",
//     "credentials": "include"
// }).then((response) => response.text())
// .then((data) => console.log(data))

// createApp({
//     data() {
//         return {
//             selectedTechnitien: "Robert",
//             technitiens: [
//                 "Robert",
//                 "Didier",
//                 "Sam",
//                 "Francis",
//             ],

//         }
//     }
// }).mount('#app');