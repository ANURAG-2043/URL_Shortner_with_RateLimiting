function shortenUrl() {
    const urlInput = document.getElementById("urlInput");
    let url = urlInput.value.trim(); // Changed to 'let' so we can modify it below
    const btn = document.getElementById("shortenBtn");
    const resultBox = document.getElementById("result");

    if (!url) {
        alert("Please enter a URL");
        urlInput.focus();
        return;
    }

    // Add protocol if it's missing (The Frontend Fix)
    if (!/^https?:\/\//i.test(url)) {
        url = 'https://' + url;
    }

    // 1. Trigger Loading State (UX improvement)
    btn.textContent = "Shortening...";
    btn.disabled = true;
    resultBox.classList.add('hidden');

    // 2. Make the actual API call
    fetch("/shorten", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ url: url })
    })
    .then(response => response.json())
    .then(data => {
        const shortUrl = data.shortUrl;

        // 3. Inject the result HTML
        resultBox.innerHTML = `
            <div>
                <span style="display:block; font-size: 0.8rem; color: #15803d; margin-bottom: 4px;">Success! Your short link:</span>
                <a href="${shortUrl}" target="_blank" class="short-url" id="generatedLink">${shortUrl}</a>
            </div>
            <button class="copy-btn" onclick="copyLink('${shortUrl}', this)">Copy</button>
        `;

        // 4. Reveal result and reset the main button
        resultBox.classList.remove('hidden');
        btn.textContent = "Shorten";
        btn.disabled = false;
    })
    .catch(error => {
        console.error("Error shortening URL:", error);
        alert("Something went wrong. Please try again.");
        
        btn.textContent = "Shorten";
        btn.disabled = false;
    });
}

// Keep your existing copyLink function down here
function copyLink(link, btnElement) {
    navigator.clipboard.writeText(link)
        .then(() => {
            btnElement.textContent = "Copied!";
            btnElement.style.backgroundColor = "#166534";
            btnElement.style.color = "white";
            
            setTimeout(() => {
                btnElement.textContent = "Copy";
                btnElement.style.backgroundColor = "white";
                btnElement.style.color = "#166534";
            }, 2000);
        })
        .catch(err => {
            console.error("Failed to copy clipboard: ", err);
            alert("Copied to clipboard!"); 
        });
}