# 🌅 Cape Town Prayer Times Dashboard

_A sunset-colored dashboard delivering accurate Islamic prayer times for Cape Town, South Africa - updated daily via API magic._

![Dashboard Preview](https://i.imgur.com/zO2cD5b.png)  
*Designed with colors inspired by Cape sunsets: #fcd0a1, #6d1a36, #53917e*

## ✨ Why This Rocks
✅ **Real-time Aladhan API integration**  
✅ **Auto-refreshing times** (set-it-and-forget-it)  
✅ **Prophet's (ﷺ) dua** with Arabic/English display  
✅ **Zero dependencies** (just pure Clojure + HTML/CSS)  

## 🚀 10-Second Setup
```bash
git clone https://github.com/Dave-Vermeulen/Prayer-Times
cd Prayer-Times
lein run  # Bam! public/index.html is born
```

## 🛠️ Tech Stack
```clojure
[🧬 Clojure]  :for-elegant-code
[🌐 Hiccup]   :html-as-data
[⚡ Aladhan]  :prayer-times-api
[🎨 CSS]      :handcrafted-styles
```

## 🔄 Auto-Update System
**Cron Job Magic** (via GitHub Actions):  
```yaml
# .github/workflows/daily-refresh.yml
- cron: '0 0 * * *'  # Midnight UTC daily
```
_Triggers rebuild → Commits new times → Vercel redeploys - fully automated!_

## 🌍 Live Demo
**[prayer-times.vercel.app](https://prayer-times.vercel.app)**  
*(Cape Towners - bookmark this!)*

---

**Q: Will times update daily?**  
**A:** Yes! The [Aladhan API](https://aladhan.com/prayer-times-api) provides fresh times daily. Our auto-deploy system ensures your dashboard always shows today's schedule.

*"Whoever eases a difficulty for a believer, Allah will ease his difficulties in this world and the Hereafter."*  
– Prophet Muhammad (ﷺ) [Sahih Muslim]*
