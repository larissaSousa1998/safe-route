function getTotalContasDicasAtivadas() {
    return new Promise(function(resolve, reject) {
        let xhr = new XMLHttpRequest();

        xhr.open("GET", `${BASE_URL}/usuarias/total-dicas-ativadas`);
    
        xhr.onreadystatechange = function() {
            if(xhr.readyState == 4 && xhr.status == 200) {
               resolve(xhr.responseText);
            }
        }
    
        xhr.send();
    });
}

function getTotalContasDicasDesativadas() {
    return new Promise(function(resolve, reject) {
        let xhr = new XMLHttpRequest();

        xhr.open("GET", `${BASE_URL}/usuarias/total-dicas-desativadas`);
    
        xhr.onreadystatechange = function() {
            if(xhr.readyState == 4 && xhr.status == 200) {
               resolve(xhr.responseText);
            }
        }
    
        xhr.send();
    });
}

function construirGraficoDicas() {
    let dicasAtivadasPromise = getTotalContasDicasAtivadas();
    let dicasDesativadasPromise = getTotalContasDicasDesativadas();

    Promise.all([dicasAtivadasPromise, dicasDesativadasPromise]).then(values => {
        var ctx = document.getElementById('dicasChart').getContext('2d');

        var myChart = new Chart(ctx, {
            type: 'doughnut',
            data: {
                labels: ['Dicas ativadas', 'Dicas desativadas'],
                datasets: [{
                    data: [values[0], values[1]],
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(54, 162, 235, 0.2)',
                    ],
                    borderColor: [
                        'rgba(255, 99, 132, 1)',
                        'rgba(54, 162, 235, 1)',
                    ],
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
    });
}

construirGraficoDicas();