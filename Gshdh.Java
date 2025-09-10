document.addEventListener('DOMContentLoaded', () => {

    // --- 1. Theme Switcher (الوضع الليلي/النهاري) ---
    const themeToggleButton = document.getElementById('theme-toggle');
    const currentTheme = localStorage.getItem('theme');

    // Check for saved theme in localStorage or user's OS preference
    if (currentTheme) {
        document.body.classList.add(currentTheme);
    } else if (window.matchMedia && window.matchMedia('(prefers-color-scheme: dark)').matches) {
        document.body.classList.add('dark-mode');
    }

    themeToggleButton.addEventListener('click', () => {
        document.body.classList.toggle('dark-mode');
        
        // Save the user's preference in localStorage
        let theme = document.body.classList.contains('dark-mode') ? 'dark-mode' : '';
        localStorage.setItem('theme', theme);
    });

    // --- 2. Dynamic Carousel (الكاروسيل) ---
    const carouselInner = document.querySelector('.carousel-inner');
    if (carouselInner) {
        const cards = Array.from(carouselInner.children);
        const prevButton = document.querySelector('.carousel-control.prev');
        const nextButton = document.querySelector('.carousel-control.next');
        let currentIndex = 0;
        
        function updateCarousel() {
            // This is a simple implementation. For a real site, you'd calculate card widths.
            // This moves the whole container.
            const offset = -currentIndex * (cards[0].offsetWidth + 32); // 32 is for margin
            carouselInner.style.transform = `translateX(${offset}px)`;
        }

        nextButton.addEventListener('click', () => {
            // Basic logic: if not at the end, move to the next.
            // A more robust solution would check visibility.
            if (currentIndex < cards.length - 2) { // Show at least 2 cards
                currentIndex++;
                updateCarousel();
            }
        });

        prevButton.addEventListener('click', () => {
            if (currentIndex > 0) {
                currentIndex--;
                updateCarousel();
            }
        });

        // On smaller screens, the carousel becomes a scrollable container via CSS,
        // so this JS logic is mainly for desktop.
    }


    // --- 3. Hover Animations & Active States ---
    // The hover animations are handled by CSS ':hover'. This section is for JS-driven effects if needed.
    // For example, adding an 'active' class to the nav link for the current page.
    const navLinks = document.querySelectorAll('header nav a');
    const currentPage = window.location.pathname.split('/').pop();
    
    navLinks.forEach(link => {
        if (link.getAttribute('href') === currentPage || (currentPage === '' && link.getAttribute('href') === 'index.html')) {
            link.classList.add('active');
        } else {
            link.classList.remove('active');
        }
    });

});
