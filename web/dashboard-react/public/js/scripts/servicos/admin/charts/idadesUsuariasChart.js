function getTotalIdades() {
    return new Promise(function(resolve, reject) {
        let xhr = new XMLHttpRequest();

        xhr.open("GET", `${BASE_URL}/usuarias/idade`);
    
        xhr.onreadystatechange = function() {
            if(xhr.readyState == 4 && xhr.status == 200) {
               resolve(JSON.parse(xhr.responseText));
            }
        }
    
        xhr.send();
    });
}

function construirGraficoIdades() {
    let dicasIdadesPromise = getTotalIdades();

   
    dicasIdadesPromise.then(result => {

        console.log(result);

        labels = [];
        data = [];
        colors = [];
        borders = [];

        result.forEach(posicao => {
            labels.push(`${posicao.idade} Anos`);
            data.push(posicao.quantidade);
            randomColor = random_rgba();
            colors.push(randomColor.color);
            borders.push(randomColor.border);
        });

        var ctx = document.getElementById('idadesUsuariasChart').getContext('2d');

        var myChart = new Chart(ctx, {
            type: 'doughnut',
            data: {
                labels: labels,
                datasets: [{
                    data: data,
                    backgroundColor: colors,
                    borderColor: borders,
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true,
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
    })
}

function random_rgba() {
    const randomBetween = (min, max) => min + Math.floor(Math.random() * (max - min + 1));
    const r = randomBetween(0, 255);
    const g = randomBetween(0, 255);
    const b = randomBetween(0, 255);
    return {color: `rgba(${r},${g},${b}, 0.2)`, border: `rgba(${r},${g},${b}, 1)`};
}

construirGraficoIdades();