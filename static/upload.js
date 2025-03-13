function uploadImage() {
    const fileInput = document.getElementById("imageInput");
    const message = document.getElementById("message");

    if (fileInput.files.length === 0) {
        message.textContent = "Please select an image.";
        return;
    }

    const file = fileInput.files[0];

    // Validate file size (max 2MB)
    if (file.size > 2 * 1024 * 1024) {
        message.textContent = "File must be less than 2MB.";
        return;
    }

    // Validate file type (JPG or PNG)
    if (!["image/jpeg", "image/png"].includes(file.type)) {
        message.textContent = "Only JPG and PNG images are allowed.";
        return;
    }

    const formData = new FormData();
    formData.append("file", file);

    fetch("http://localhost:8080/api/images/upload", {
        method: "POST",
        body: formData
    })
    .then(response => response.text())
    .then(data => {
        message.textContent = data.message || "Image uploaded successfully!";
        message.style.color = "green";
    })
    .catch(error => {
        console.error("Error uploading image:", error);
        message.textContent = "Upload failed. Try again.";
    });
}
