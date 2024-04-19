function performSearch(event) {
    event.preventDefault(); // 폼 제출을 막습니다.

    // 검색어를 가져옵니다.
    const query = document.querySelector("[name='query']").value;

    // 여기에 검색 기능을 추가하세요.
    console.log("검색어:", query);

    // 검색어를 사용하여 원하는 작업을 수행합니다.
    // 예를 들어, 백엔드로 요청을 보내서 검색 결과를 가져올 수 있습니다.
    const url = `/search?query=${encodeURIComponent(query)}`;

    fetch(url)
        .then(response => response.json())
        .then(data => {
            // 검색 결과를 처리합니다.
            console.log("검색 결과:", data);
            // 결과를 웹 페이지에 표시하는 코드를 여기에 추가하세요.
        })
        .catch(error => {
            console.error("검색 오류:", error);
        });
}