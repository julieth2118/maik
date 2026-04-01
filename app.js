/* ========================================================
   SPECTRA NEWS HUB - 6 ENDPOINTS COMPLETOS v2.0
   Estefany Julieth Ochoa Barrera • SENA 2024
   Entertainment Italia • TheNewsAPI.com
======================================================= */

const API_CORE = 'https://api.thenewsapi.com/v1';
let activePanel = 'prime';

// === NAVIGATION SYSTEM ===
function switchPanel(panelId, chip) {
  document.querySelectorAll('.control-matrix').forEach(p => p.classList.remove('active'));
  document.querySelectorAll('.terminal-screen').forEach(s => s.classList.remove('active'));
  
  document.getElementById(`panel-${panelId}`).classList.add('active');
  document.getElementById(`screen-${panelId}`).classList.add('active');
  
  document.querySelectorAll('.nav-chip').forEach(c => c.classList.remove('active'));
  chip.classList.add('active');
  
  activePanel = panelId;
}

// === AUTH SYSTEM ===
function getToken(panelId) {
  const tokenId = `${panelId}Token`;
  const token = document.getElementById(tokenId)?.value.trim();
  if (!token) {
    showAlert('🔐 Access Denied', `Enter API token in ${panelId.toUpperCase()} panel`);
    return null;
  }
  return token;
}

// === LOADING SYSTEM ===
function activateVeil() {
  document.getElementById('veil').classList.add('active');
}
function deactivateVeil() {
  document.getElementById('veil').classList.remove('active');
}

// === ALERT SYSTEM ===
function showAlert(title, message) {
  const alertDiv = document.createElement('div');
  alertDiv.style.cssText = `
    position: fixed; top: 20px; right: 20px; z-index: 1001;
    background: linear-gradient(135deg, #00d4ff, #7c3aed);
    color: white; padding: 1.5rem 2rem; border-radius: 20px;
    box-shadow: 0 20px 40px rgba(0,212,255,0.3);
    font-family: 'Orbitron', monospace; font-size: 1rem;
    animation: slideIn 0.4s ease;
  `;
  alertDiv.innerHTML = `<strong>${title}</strong><br>${message}`;
  document.body.appendChild(alertDiv);
  
  setTimeout(() => {
    alertDiv.remove();
  }, 4000);
}

function renderError(containerId, message) {
  document.getElementById(containerId).innerHTML = `
    <div style="text-align: center; padding: 4rem 2rem; color: #ff6b6b;">
      <div style="font-size: 5rem; margin-bottom: 1rem;">⚠️</div>
      <h3 style="font-family: 'Orbitron', monospace; font-size: 1.8rem; margin-bottom: 1rem;">
        Quantum Error
      </h3>
      <p style="font-size: 1.1rem; line-height: 1.6; max-width: 500px; margin: 0 auto;">
        ${message}
      </p>
    </div>
  `;
}

// ========================================
// 1️⃣ PRIME NEWS - /news/top
// ========================================
async function activatePrime() {
  const token = getToken('prime');
  if (!token) return;
  
  const lang = document.getElementById('primeLang').value;
  const cat = document.getElementById('primeCat').value;
    const count = document.getElementById('primeCount').value;
  
  const outputZone = document.getElementById('primeOutput');
  activateVeil();
  
  try {
    const params = new URLSearchParams({
      api_token: token,
      locale: 'it',
      language: lang,
      categories: cat,
      limit: count
    });
    
    const response = await fetch(`${API_CORE}/news/top?${params}`);
    const data = await response.json();
    
    if (!response.ok) throw new Error(data.message || 'Prime news error');
    if (!data.data?.length) throw new Error('No top headlines found');
    
    renderNewsGrid('primeOutput', data.data, 'Top Entertainment News');
    
  } catch (error) {
    renderError('primeOutput', error.message);
  } finally {
    deactivateVeil();
  }
}

// ========================================
// 2️⃣ NEWS STREAM - /news/all
// ========================================
async function activateStream() {
  const token = getToken('stream');
  if (!token) return;
  
  const lang = document.getElementById('streamLang').value;
  const cat = document.getElementById('streamCat').value;
  const count = document.getElementById('streamCount').value;
  
  activateVeil();
  const outputZone = document.getElementById('streamOutput');
  
  try {
    const params = new URLSearchParams({
      api_token: token,
      locale: 'it',
      language: lang,
      categories: cat,
      limit: count
    });
    
    const response = await fetch(`${API_CORE}/news/all?${params}`);
    const data = await response.json();
    
    if (!response.ok) throw new Error(data.message || 'Stream error');
    if (!data.data?.length) throw new Error('No stream news found');
    
    renderNewsGrid('streamOutput', data.data, 'Live News Stream');
    
  } catch (error) {
    renderError('streamOutput', error.message);
  } finally {
    deactivateVeil();
  }
}

// ========================================
// 3️⃣ QUANTUM SEARCH - /news/search
// ========================================
async function activateQuantum() {
  const token = getToken('quantum');
  if (!token) return;
  
  const query = document.getElementById('searchQuery').value.trim();
  if (!query) {
    showAlert('🔍 Search Error', 'Enter keywords to search');
    return;
  }
  
  const lang = document.getElementById('quantumLang').value;
  const count = document.getElementById('quantumCount').value;
  
  activateVeil();
  
  try {
    const params = new URLSearchParams({
      api_token: token,
      search: query,
      language: lang,
      limit: count
    });
    
    const response = await fetch(`${API_CORE}/news/search?${params}`);
    const data = await response.json();
    
    if (!response.ok) throw new Error(data.message || 'Search error');
    if (!data.data?.length) throw new Error(`No results for "${query}"`);
    
    renderNewsGrid('quantumOutput', data.data, `Search: "${query}"`);
    
  } catch (error) {
    renderError('quantumOutput', error.message);
  } finally {
    deactivateVeil();
  }
}

// ========================================
// 4️⃣ ECHO SIMILAR - /news/similar
// ========================================
async function activateEcho() {
  const token = getToken('echo');
  if (!token) return;
  
  const uuid = document.getElementById('echoId').value.trim();
  if (!uuid || uuid === 'demo-123') {
    showAlert('🔄 Echo Error', 'Paste a real article UUID');
    return;
  }
  
  const limit = document.getElementById('echoLimit').value;
  
  activateVeil();
  
  try {
    const params = new URLSearchParams({
      api_token: token,
      article: uuid,
      limit: limit
    });
    
    const response = await fetch(`${API_CORE}/news/similar?${params}`);
    const data = await response.json();
    
    if (!response.ok) throw new Error(data.message || 'Similar articles error');
    if (!data.data?.length) throw new Error('No similar articles found');
    
    renderNewsGrid('echoOutput', data.data, `Similar to UUID: ${uuid.slice(0,12)}...`);
    
  } catch (error) {
    renderError('echoOutput', error.message);
  } finally {
    deactivateVeil();
  }
}

// ========================================
// 5️⃣ SOURCES MATRIX - /news/sources
// ========================================
async function activateMatrix() {
  const token = getToken('matrix');
  if (!token) return;
  
  const lang = document.getElementById('matrixLang').value;
  const cat = document.getElementById('matrixCat').value;
  
  activateVeil();
  
  try {
    const params = new URLSearchParams({
      api_token: token,
      language: lang,
      categories: cat
    });
    
    const response = await fetch(`${API_CORE}/news/sources?${params}`);
    const data = await response.json();
    
    if (!response.ok) throw new Error(data.message || 'Sources error');
    if (!data.data?.length) throw new Error('No sources found');
    
    renderSourcesGrid('matrixOutput', data.data);
    
  } catch (error) {
    renderError('matrixOutput', error.message);
  } finally {
    deactivateVeil();
  }
}

// ========================================
// 6️⃣ CORE DETAIL - /news/detail
// ========================================
async function activateCore() {
  const token = getToken('core');
  if (!token) return;
  
  const uuid = document.getElementById('coreId').value.trim();
  if (!uuid || uuid === 'demo-456') {
    showAlert('📄 Core Error', 'Paste a real article UUID');
    return;
  }
  
  activateVeil();
  
  try {
    const params = new URLSearchParams({
      api_token: token,
      article: uuid
    });
    
    const response = await fetch(`${API_CORE}/news?${params}`);
    const data = await response.json();
    
    if (!response.ok) throw new Error(data.message || 'Article detail error');
    if (!data.data?.length) throw new Error('Article not found');
    
    renderCoreDetail('coreOutput', data.data[0]);
    
  } catch (error) {
    renderError('coreOutput', error.message);
  } finally {
    deactivateVeil();
  }
}

// ========================================
// RENDERING ENGINE
// ========================================
function renderNewsGrid(containerId, articles, title) {
  let html = `
    <div class="stats-matrix">
      <div class="stat-unit">
        <div class="stat-value">${articles.length}</div>
        <div>${title}</div>
      </div>
      <div class="stat-unit">
        <div class="stat-value">🇮🇹</div>
        <div>Italian Sources</div>
      </div>
    </div>
    <div class="news-grid">
  `;
  
  articles.slice(0, 12).forEach(article => {
    html += renderCyberCard(article);
  });
  
  html += `</div>`;
  document.getElementById(containerId).innerHTML = html;
}

function renderSourcesGrid(containerId, sources) {
  let html = `
    <div class="stats-matrix">
      <div class="stat-unit">
        <div class="stat-value">${sources.length}</div>
        <div>News Sources</div>
      </div>
      <div class="stat-unit">
        <div class="stat-value">🎬</div>
        <div>Entertainment</div>
      </div>
    </div>
  `;
  
  sources.forEach(source => {
    html += renderSourceCard(source);
  });
  
  document.getElementById(containerId).innerHTML = html;
}

function renderCoreDetail(containerId, article) {
  const imgHtml = article.image_url 
    ? `<img class="card-media" src="${article.image_url}" alt="${article.title}">`
    : `<div class="card-placeholder" style="height: 300px;">📄</div>`;
    
  document.getElementById(containerId).innerHTML = `
    <div class="cyber-card" style="max-width: 800px; margin: 0 auto;">
      ${imgHtml}
      <div class="card-content" style="padding: 3rem;">
        <div class="card-category">${(article.categories || ['News']).join(' • ')}</div>
        <h1 class="card-title" style="font-size: 2.2rem; margin-bottom: 1.5rem;">
          ${article.title || 'No title'}
        </h1>
        <div style="background: rgba(255,255,255,0.05); padding: 2rem; border-radius: 15px; margin: 2rem 0;">
          <p style="line-height: 1.8; font-size: 1.1rem;">${article.description || article.content || 'No description'}</p>
        </div>
        <div class="card-meta" style="font-size: 1rem;">
          <span>📰 ${article.source || 'Unknown'}</span>
          <span>📅 ${formatQuantumDate(article.published_at)}</span>
        </div>
        ${article.url ? `<div class="action-row" style="margin-top: 2rem;">
          <a class="read-btn" href="${article.url}" target="_blank" style="padding: 1rem 2rem; font-size: 1rem;">🔗 Read Full Article</a>
        </div>` : ''}
      </div>
    </div>
  `;
}

function renderCyberCard(article) {
  const imgHtml = article.image_url 
    ? `<img class="card-media" src="${article.image_url}" alt="${article.title}" onerror="this.style.display='none'; this.nextElementSibling.style.display='flex'">`
    : `<div class="card-placeholder">🎬</div>`;
    
  return `
    <article class="cyber-card">
      ${imgHtml}
      <div class="card-content">
        <div class="card-category">${(article.categories || ['Entertainment']).join(' • ')}</div>
        <h3 class="card-title">
          <a href="${article.url || '#'}" target="_blank">${article.title || 'No title'}</a>
        </h3>
        <p class="card-description">${article.description || article.snippet || 'No description available'}</p>
        <div class="card-meta">
          <span>📰 ${article.source || 'Unknown'}</span>
          <span>${formatQuantumDate(article.published_at)}</span>
        </div>
        <div class="action-row">
          <a class="read-btn" href="${article.url || '#'}" target="_blank">Read →</a>
          <button class="copy-btn" onclick="quantumCopy('${article.uuid || ''}')">📋</button>
        </div>
      </div>
    </article>
  `;
}

function renderSourceCard(source) {
  return `
    <div class="cyber-card">
      <div class="card-content" style="padding: 2.5rem; text-align: center;">
        <div class="card-category" style="font-size: 1rem;">${source.category || 'General'}</div>
        <h3 class="card-title" style="font-size: 1.6rem;">
          ${source.name || 'Unknown Source'}
        </h3>
        <p style="color: rgba(255,255,255,0.8); margin: 1rem 0 1.5rem;">
          ${source.description || 'News source'} • ${source.country || 'IT'}
        </p>
        <div class="action-row">
          <a class="read-btn" href="${source.url || '#'}" target="_blank">🌐 Visit</a>
          <span style="color: var(--cyan-glow); font-size: 0.9rem;">
            ${source.language?.toUpperCase() || 'IT'}
          </span>
        </div>
      </div>
    </div>
  `;
}

// ========================================
// UTILITIES
// ========================================
function formatQuantumDate(isoDate) {
  if (!isoDate) return '—';
  try {
    return new Date(isoDate).toLocaleDateString('it-IT', {
      month: 'short', day: 'numeric', hour: '2-digit', minute: '2-digit'
    });
  } catch {
    return isoDate.slice(0, 10);
  }
}

function quantumCopy(uuid) {
  if (uuid) {
    navigator.clipboard.writeText(uuid).then(() => {
      showAlert('✅ UUID Copied', uuid.slice(0,12) + '...');
      
      // Auto-fill UUID fields
      ['echoId', 'coreId'].forEach(id => {
        const field = document.getElementById(id);
        if (field) field.value = uuid;
      });
    });
  }
}

// ========================================
// INITIALIZATION
// ========================================
document.addEventListener('DOMContentLoaded', function() {
  // Navigation listeners
  document.querySelectorAll('.nav-chip').forEach(chip => {
    chip.addEventListener('click', function() {
      switchPanel(this.dataset.panel, this);
    });
  });
  
  // Enter key support
  document.querySelectorAll('.code-input').forEach(input => {
    input.addEventListener('keypress', function(e) {
      if (e.key === 'Enter') {
        const panel = activePanel;
        if (panel === 'prime') activatePrime();
        else if (panel === 'stream') activateStream();
        // ... other panels
      }
    });
  });
  
  // PWA
  if ('serviceWorker' in navigator) {
    navigator.serviceWorker.register('sw.js').catch(() => {});
  }
  
  showAlert('🚀 SPECTRA HUB', '6/6 Endpoints Ready! Get token at thenewsapi.com');
});