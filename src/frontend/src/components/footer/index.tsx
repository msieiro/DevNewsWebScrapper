import React from "react";

export const Footer: React.FC = () => {
    return (
        <div
            className="text-center py-1 bg-gray-800"
            style={{ backgroundColor: "var(--onyx)" }}
        >
            <h1 className="text-white">
                Hecho con 💖 por{" "}
                <a href="https://github.com/msieiro" target="_blank">
                    msieiro
                </a>
            </h1>
        </div>
    );
};
