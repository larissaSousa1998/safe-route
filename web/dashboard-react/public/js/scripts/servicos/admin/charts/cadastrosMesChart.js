function getTotalIdades() {
    return new Promise(function(resolve, reject) {
        let xhr = new XMLHttpRequest();

        xhr.open("GET", `${BASE_URL}/usuarias/total-mes`);
    
        xhr.onreadystatechange = function() {
            if(xhr.readyState == 4 && xhr.status == 200) {
               resolve(JSON.parse(xhr.responseText));
            }
        }
    
        xhr.send();
    });
}

function construirGraficoCadastros() {
    let cadastrosMesPromise = getTotalIdades();
   
    cadastrosMesPromise.then(result => {

        console.log(result);

        data = [];

        result.forEach(posicao => {
            data[posicao.numeroMes] = posicao.cadastros;   
        });

        var ctx = document.getElementById('cadastrosMesChart').getContext('2d');

        var myChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: ["janeiro", "fevereiro", "março", "abril", "maio", "junho", "julho", "agosto", "setembro", "outubro", "novembro", "dezembro"],
                datasets: [{
                    data: data,
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(255, 206, 86, 0.2)',
                        'rgba(75, 192, 192, 0.2)',
                        'rgba(153, 102, 255, 0.2)',
                        'rgba(255, 159, 64, 0.2)',
                        'rgba(205, 99, 132, 0.2)',
                        'rgba(24, 162, 235, 0.2)',
                        'rgba(213, 206, 86, 0.2)',
                        'rgba(97, 192, 192, 0.2)',
                        'rgba(133, 102, 255, 0.2)',
                        'rgba(155, 159, 64, 0.2)'
                    ],
                    borderColor: [
                        'rgba(255, 99, 132, 1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(255, 206, 86, 1)',
                        'rgba(75, 192, 192, 1)',
                        'rgba(153, 102, 255, 1)',
                        'rgba(255, 159, 64, 1)',
                        'rgba(205, 99, 132, 1)',
                        'rgba(24, 162, 235, 1)',
                        'rgba(213, 206, 86, 1)',
                        'rgba(97, 192, 192, 1)',
                        'rgba(133, 102, 255, 1)',
                        'rgba(155, 159, 64, 1)'
                    ],
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true,
                plugins: {
                    legend: {
                        display: false
                    }
                },
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
    })
}

construirGraficoCadastros();