import { Box } from "@mui/material";
import { Outlet } from "react-router-dom";

export default function RootLayout() {
  return (
    <Box>
      <main>
        <Outlet />
      </main>
    </Box>
  );
}
