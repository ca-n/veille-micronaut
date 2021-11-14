const urlBase = "http://localhost:9191/session"
const SessionService = {
  // getAllSession [GET]
  getAllSessions: async () => {
    const res = await fetch(urlBase + '/allSessions');
    const data = await res.json();
    return data;
  }
}

export default SessionService;
