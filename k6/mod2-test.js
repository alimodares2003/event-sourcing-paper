import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
    vus: 50,
    duration: '1m',
};

function uuidv4() {
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
        const r = Math.random() * 16 | 0, v = c === 'x' ? r : (r & 0x3 | 0x8);
        return v.toString(16);
    });
}
function generateSubmitRequest() {
    const randomScore = Math.floor(Math.random() * 21);
    const randomMinutes = Math.floor(Math.random() * (60 - 10 + 1)) + 10;

    const future = new Date(Date.now() + randomMinutes * 60 * 1000);
    const evaluationTime = future.toTimeString().split(' ')[0]; // => "HH:mm:ss"


    return JSON.stringify({
        examId: 1,
        studentId: uuidv4(),
        teacherId: "9f07b8b3-753e-49c5-83b0-ff6ac58907fc",  // fixed for mod3 stats lookup
        score: randomScore,
        evaluationTime: evaluationTime
    });
}
export default function () {
    const payload = generateSubmitRequest();
    const headers = { 'Content-Type': 'application/json' };

    const submitRes = http.post('http://localhost:8080/commands/exams/submit', payload, { headers });
    check(submitRes, { 'command status is 200': (r) => r.status === 200 });

    // const queryRes = http.get('http://localhost:8080/reports/exams');
    // check(queryRes, { 'query count is 200': (r) => r.status === 200 });

    sleep(1);
}
