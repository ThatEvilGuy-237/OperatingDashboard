const { exec } = require('child_process');

exec('start cmd /k "cd backend && mvn spring-boot:run"');

const frontend = exec('cd frontend && npm run dev');

frontend.stdout.pipe(process.stdout);
frontend.stderr.pipe(process.stderr);