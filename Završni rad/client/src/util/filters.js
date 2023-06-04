export function formatTime(timestamp){
        const date = new Date(timestamp);

          const minutes = String(date.getUTCMinutes()).padStart(2, '0');
          const seconds = String(date.getUTCSeconds()).padStart(2, '0');
          const milliseconds = String(date.getUTCMilliseconds()).padStart(3, '0');

          return `${minutes}:${seconds}.${milliseconds}`;
}
export function formatYear(timestamp){
    const date = new Date(timestamp)

    const year = date.getFullYear();

    return year;
}
