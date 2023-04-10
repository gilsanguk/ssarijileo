// Path: 'chart/'
import Image from 'next/image';
import { GetServerSideProps } from 'next';

import axios from 'axios';

import type { ChartItem } from '@/pages';
import ChartTop from '@/components/chart/ChartTop';
import SoundBar from '@/components/common/SoundBar';
import ChartList from '@/components/chart/ChartList';

import styles from '@/styles/chart/Chart.module.scss';
import { useCookie } from '@/hooks/useCookie';

export const getServerSideProps: GetServerSideProps = async context => {
  const cookieString = context.req.headers.cookie || '';
  const cookies = useCookie(cookieString);
  const token = cookies.Authorization;
  if (!token) return { redirect: { destination: '/', permanent: false } };
  const dailyRes = await axios.get(
    'http://i8b302.p.example.io:8000/api/v1/ranking/daily',
    {
      headers: {
        Authorization: token,
      },
    },
  );
  const weeklyRes = await axios.get(
    'http://i8b302.p.example.io:8000/api/v1/ranking/weekly',
    {
      headers: {
        Authorization: token,
      },
    },
  );
  const monthlyRes = await axios.get(
    'http://i8b302.p.example.io:8000/api/v1/ranking/monthly',
    {
      headers: {
        Authorization: token,
      },
    },
  );

  return {
    props: {
      daily: dailyRes.data,
      weekly: weeklyRes.data,
      monthly: monthlyRes.data,
    },
  };
};

function Chart(props: {
  daily: ChartItem[];
  weekly: ChartItem[];
  monthly: ChartItem[];
}) {
  const { daily, weekly, monthly } = props;
  // 이번 달, 주차, 날짜 구하기
  const date = new Date();
  const year = date.getFullYear();
  const month = date.getMonth() + 1;
  const day = date.getDate();
  const firstDay = new Date(year, month, 1).getDay();
  const week = Math.ceil((day + firstDay) / 7);

  return (
    <>
      <ChartTop day={daily} week={weekly} month={monthly} />
      <SoundBar />
      <div className={styles.container}>
        <div className={styles.list}>
          <div className={styles.title}>
            <span className={styles.context}>{month}월 TOP 100</span>
            <Image
              src="img/chart/chart_rank_image.svg"
              width={50}
              height={50}
              alt="rank"
              className={styles.icon}
            />
          </div>
          <ChartList chartList={monthly} />
        </div>
        <hr className={styles.line} />
        <div className={styles.list}>
          <div className={styles.title}>
            <span className={styles.context}>
              {month}월 {week}째주 TOP 100
            </span>
            <Image
              src="img/chart/chart_rank_image.svg"
              width={50}
              height={50}
              alt="rank"
              className={styles.icon}
            />
          </div>
          <ChartList chartList={weekly} />
        </div>
        <hr className={styles.line} />
        <div className={styles.list}>
          <div className={styles.title}>
            <span className={styles.context}>
              {month}월 {day}일 TOP 100
            </span>
            <Image
              src="img/chart/chart_rank_image.svg"
              width={50}
              height={50}
              alt="rank"
              className={styles.icon}
            />
          </div>
          <ChartList chartList={daily} />
        </div>
      </div>
      <SoundBar />
    </>
  );
}

export default Chart;
