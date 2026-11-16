const CACHE = 'coin-flip-v3';
const FILES = ['./', 'index.html', 'manifest.json', 'icons/heads.png', 'icons/tails.png', 'icons/icon-180.jpg', 'icons/icon-192.jpg', 'icons/icon-512.jpg'];
self.addEventListener('install', e => { self.skipWaiting(); e.waitUntil(caches.open(CACHE).then(c => c.addAll(FILES))); });
self.addEventListener('activate', e => { self.clients.claim(); e.waitUntil(caches.keys().then(ks => Promise.all(ks.filter(k => k !== CACHE).map(k => caches.delete(k))))); });
// Network first so edits show up right away; the saved copy is only the offline fallback.
self.addEventListener('fetch', e => e.respondWith(fetch(e.request).catch(() => caches.match(e.request))));
