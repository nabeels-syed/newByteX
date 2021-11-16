import React from "react";

function eventBannerLoading(Component) {
  return function LoadingComponent({ isLoading, ...props }) {
    if (!isLoading) return <Component {...props} />;
    return (
      <p style={{ textAlign: "center", fontSize: "15px", fontWeight: "bold" }}>
        Hang tight, fetching events...
      </p>
    );
  };
}

export default eventBannerLoading;
